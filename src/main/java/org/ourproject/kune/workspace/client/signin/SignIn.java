package org.ourproject.kune.workspace.client.signin;

import org.ourproject.kune.platf.client.dto.StateToken;

public interface SignIn {

    void doSignIn(StateToken previousStateToken);

    void hide();

}
