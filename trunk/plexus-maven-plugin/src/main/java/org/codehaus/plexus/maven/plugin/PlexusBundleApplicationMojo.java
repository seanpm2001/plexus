package org.apache.maven.plugin.plexus;

/*
 * Copyright (c) 2004, Codehaus.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.io.File;

import org.apache.maven.plugin.AbstractPlugin;
import org.apache.maven.plugin.PluginExecutionRequest;
import org.apache.maven.plugin.PluginExecutionResponse;

import org.codehaus.plexus.builder.application.ApplicationBuilder;

/**
 * @goal bundle-application
 *
 * @requiresDependencyResolution
 *
 * @description Packages the runtime into a redistributable jar file.
 *
 * @prereq plexus:app
 *
 * @parameter name="basedir"
 * type="String"
 * required="true"
 * validator=""
 * expression="#basedir"
 * description=""
 *
 * @parameter name="finalName"
 * type="java.lang.String"
 * required="true"
 * validator=""
 * expression="#maven.final.name"
 * description=""
 *
 * @parameter name="applicationBuilder"
 * type="org.codehaus.plexus.builder.application.ApplicationBuilder"
 * required="true"
 * validator=""
 * expression="#component.org.codehaus.plexus.builder.application.ApplicationBuilder"
 * description=""
 *
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 * @version $Id$
 */
public class PlexusBundleApplicationMojo
    extends AbstractPlugin
{
    public void execute( PluginExecutionRequest request, PluginExecutionResponse response )
        throws Exception
    {
        // ----------------------------------------------------------------------
        //
        // ----------------------------------------------------------------------

        String basedir = (String) request.getParameter( "basedir" );

        String finalName = (String) request.getParameter( "finalName" );

        ApplicationBuilder builder = (ApplicationBuilder) request.getParameter( "applicationBuilder" );

        // ----------------------------------------------------------------------
        //
        // ----------------------------------------------------------------------

        File runtimeDirectory = new File( basedir, "plexus-application" );

        File outputFile = new File( basedir, finalName + "-application.jar" );

        builder.bundle( outputFile, runtimeDirectory );
    }
}
