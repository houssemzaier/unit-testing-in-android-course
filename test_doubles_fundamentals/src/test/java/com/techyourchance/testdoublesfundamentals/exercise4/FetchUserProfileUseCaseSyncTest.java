package com.techyourchance.testdoublesfundamentals.exercise4;

import com.techyourchance.testdoublesfundamentals.example4.networking.NetworkErrorException;
import com.techyourchance.testdoublesfundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import com.techyourchance.testdoublesfundamentals.exercise4.users.User;
import com.techyourchance.testdoublesfundamentals.exercise4.users.UsersCache;

import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FetchUserProfileUseCaseSyncTest {
    FetchUserProfileUseCaseSync SUT;
    private UsersCacheTD usersCache = new UsersCacheTD();
    private UserProfileHttpEndpointSyncTD userProfileHttpEndpointSync = new UserProfileHttpEndpointSyncTD();

    @Before
    public void setup() {
        //GIVEN
        SUT = new FetchUserProfileUseCaseSync(userProfileHttpEndpointSync, usersCache);
    }

    @Test
    public void fetchUserProfileSync_successCallToEndPoint_SUCCESS_userIsCached() {
        //WHEN first dependency change input
        userProfileHttpEndpointSync.endpointResultStatus = UserProfileHttpEndpointSync.EndpointResultStatus.SUCCESS;

        FetchUserProfileUseCaseSync.UseCaseResult useCaseResult = SUT.fetchUserProfileSync(USER_ID);

        //test interaction on other dependencies
        assertThat(usersCache.users.size(), is(1));
        assertThat(usersCache.users.get(USER_ID).getFullName(), is(USER_NAME));
        assertThat(usersCache.users.get(USER_ID).getImageUrl(), is(IMAGE_URL));
        assertThat(usersCache.users.get(USER_ID).getUserId(), is(USER_ID));
        assertThat(userProfileHttpEndpointSync.userId, is(USER_ID));
        //test result
        assertThat(useCaseResult, is(FetchUserProfileUseCaseSync.UseCaseResult.SUCCESS));
    }

    @Test
    public void fetchUserProfileSync_failCallToEndPoint_SERVER_ERROR_userNotCached() {
        //WHEN first dependency change input
        userProfileHttpEndpointSync.endpointResultStatus = UserProfileHttpEndpointSync.EndpointResultStatus.SERVER_ERROR;

        //THEN
        FetchUserProfileUseCaseSync.UseCaseResult useCaseResult = SUT.fetchUserProfileSync(USER_NAME);
        //test interaction on other dependencies
        assertTrue(usersCache.users.isEmpty());
        assertThat(userProfileHttpEndpointSync.userId, is(USER_NAME));
        //test result
        assertThat(useCaseResult, is(FetchUserProfileUseCaseSync.UseCaseResult.NETWORK_ERROR));
    }

    @Test
    public void fetchUserProfileSync_failCallToEndPoint_AUTH_ERROR_userNotCached() {
        //when first dependency change input
        userProfileHttpEndpointSync.endpointResultStatus = UserProfileHttpEndpointSync.EndpointResultStatus.AUTH_ERROR;

        FetchUserProfileUseCaseSync.UseCaseResult useCaseResult = SUT.fetchUserProfileSync(USER_NAME);

        //test interaction on other dependencies
        assertTrue(usersCache.users.isEmpty());
        assertThat(userProfileHttpEndpointSync.userId, is(USER_NAME));
        //test result
        assertThat(useCaseResult, is(FetchUserProfileUseCaseSync.UseCaseResult.FAILURE));
    }

    @Test
    public void fetchUserProfileSync_failCallToEndPoint_GENERAL_ERROR_userNotCached() {
        //when first dependency change input
        userProfileHttpEndpointSync.endpointResultStatus = UserProfileHttpEndpointSync.EndpointResultStatus.GENERAL_ERROR;

        FetchUserProfileUseCaseSync.UseCaseResult useCaseResult = SUT.fetchUserProfileSync(USER_NAME);

        //test interaction on other dependencies
        assertTrue(usersCache.users.isEmpty());
        assertThat(userProfileHttpEndpointSync.userId, is(USER_NAME));
        //test result
        assertThat(useCaseResult, is(FetchUserProfileUseCaseSync.UseCaseResult.FAILURE));
    }

    //////////////////////////////////////////////// mocked dependencies
    private static final String IMAGE_URL = "imageUrl";
    private static final String USER_NAME = "userName";
    private static final String USER_ID = "userID";

    private static class UsersCacheTD implements UsersCache {

        private Map<String, User> users = new HashMap<>();

        @Override
        public void cacheUser(User user) {
            this.users.put(user.getUserId(), user);
        }

        @Nullable
        @Override
        public User getUser(String userId) {
            return users.get(userId);
        }
    }

    private static class UserProfileHttpEndpointSyncTD implements UserProfileHttpEndpointSync {
        //SUCCESS,
        //AUTH_ERROR,
        //SERVER_ERROR,
        //GENERAL_ERROR
        EndpointResultStatus endpointResultStatus = EndpointResultStatus.SUCCESS;

        String userId = "";

        @Override
        public EndpointResult getUserProfile(String userId) throws NetworkErrorException {
            this.userId = userId;
            if (endpointResultStatus == EndpointResultStatus.SERVER_ERROR) {
                throw new NetworkErrorException();
            }
            return new EndpointResult(endpointResultStatus, this.userId, USER_NAME, IMAGE_URL);
        }
    }

}
