/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.it.ezlink.ms;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

/**
 *
 * @author arquimedes
 */
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("br.com.infotera.it.ezlink.ms");
        register(EntityFilteringFeature.class);
        EncodingFilter.enableFor(this, GZipEncoder.class);
    }
}
