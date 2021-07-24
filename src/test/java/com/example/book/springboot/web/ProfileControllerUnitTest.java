package com.example.book.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api. Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void read_real_profile(){
        //given
        String expectedProfile="real";
        MockEnvironment env=new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller=new ProfileController(env);

        String profile=controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void read_the_first_when_no_real_profile(){
        //given
        String expectedProfile="oauth";
        MockEnvironment env=new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller=new ProfileController(env);

        String profile=controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void read_default_when_no_active_profile(){
        //given
        String expectedProfile="default";
        MockEnvironment env=new MockEnvironment();

        ProfileController controller=new ProfileController(env);

        String profile=controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }
}
