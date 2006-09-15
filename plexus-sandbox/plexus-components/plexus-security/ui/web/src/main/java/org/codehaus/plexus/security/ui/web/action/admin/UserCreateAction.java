package org.codehaus.plexus.security.ui.web.action.admin;

/*
 * Copyright 2001-2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.codehaus.plexus.security.ui.web.action.AbstractUserCredentialsAction;
import org.codehaus.plexus.security.ui.web.model.UserCredentials;
import org.codehaus.plexus.security.user.User;

/**
 * UserCreateAction 
 *
 * @author <a href="mailto:joakim@erdfelt.com">Joakim Erdfelt</a>
 * @version $Id$
 * 
 * @plexus.component role="com.opensymphony.xwork.Action"
 *                   role-hint="pss-admin-user-create"
 *                   instantiation-strategy="per-lookup"
 */
public class UserCreateAction
    extends AbstractUserCredentialsAction
{
    // ------------------------------------------------------------------
    // Action Entry Points - (aka Names)
    // ------------------------------------------------------------------

    public String edit()
    {
        if ( user == null )
        {
            user = new UserCredentials();
        }

        return INPUT;
    }

    public String submit()
    {
        if ( user == null )
        {
            user = new UserCredentials();
            addActionError( "Invalid user credentials." );
            return ERROR;
        }
        
        validateCredentialsLoose();

        // NOTE: Do not perform Password Rules Validation Here.

        if ( manager.userExists( user.getUsername() ) )
        {
            // Means that the role name doesn't exist.
            // We need to fail fast and return to the previous page.
            addActionError( "User '" + user.getUsername() + "' already exists." );
        }

        if ( hasActionErrors() || hasFieldErrors() )
        {
            return ERROR;
        }

        User u = manager.createUser( user.getUsername(), user.getFullName(), user.getEmail() );
        u.setPassword( user.getPassword() );

        // Disable Password Rules for this creation.
        securityPolicy.setEnabled( false );
        try
        {
            manager.addUser( u );
        }
        finally
        {
            securityPolicy.setEnabled( true );
        }

        return SUCCESS;
    }
}
