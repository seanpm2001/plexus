package org.codehaus.plexus.lifecycle.phase;

import org.apache.avalon.framework.parameters.Parameterizable;
import org.apache.avalon.framework.parameters.Parameters;
import org.apache.avalon.framework.parameters.Reparameterizable;
import org.codehaus.plexus.lifecycle.LifecycleHandler;
import org.codehaus.plexus.service.repository.ComponentHousing;

public class ReparameterizePhase
    extends AbstractPhase
{
    public void execute( ComponentHousing housing, LifecycleHandler handler )
        throws Exception
    {
        Object object = housing.getComponent();
        Parameters parameters = (Parameters) handler.getEntities().get( "parameters" );

        if ( object instanceof Parameterizable )
        {
            if ( null == parameters )
            {
                final String message = "parameters is null";
                throw new IllegalArgumentException( message );
            }
            ( (Reparameterizable) object ).parameterize( parameters );
        }
    }
}
