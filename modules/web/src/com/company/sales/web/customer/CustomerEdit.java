package com.company.sales.web.customer;

import com.haulmont.charts.gui.components.map.MapViewer;
import com.haulmont.charts.gui.map.model.GeoPoint;
import com.haulmont.charts.gui.map.model.Marker;
import com.haulmont.charts.gui.map.model.listeners.MapInitListener;
import com.haulmont.charts.gui.map.model.listeners.click.MapClickListener;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sales.entity.Customer;

import javax.inject.Inject;
import java.util.Map;

public class CustomerEdit extends AbstractEditor<Customer> {

    public static final String CUSTOMER_ICON = "VAADIN/images/customer.png";

    @Inject
    private MapViewer locationMap;

    private Marker marker;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        
        locationMap.addMapInitListener((center, zoom, boundNE, boundSW) -> {
            GeoPoint newCenter = locationMap.createGeoPoint(53.490905, -2.249558);
            locationMap.setCenter(newCenter);
            locationMap.setZoom(5);
        });

        locationMap.addMapClickListener(event -> {
            if (marker != null) {
                locationMap.removeMarker(marker);
            }
            marker = locationMap.createMarker();
            marker.setIcon(locationMap.createMarkerImage(CUSTOMER_ICON));
            marker.setPosition(event.getPosition());
            locationMap.addMarker(marker);

            getItem().setLocationLat(event.getPosition().getLatitude());
            getItem().setLocationLng(event.getPosition().getLongitude());
        });
    }

    @Override
    protected void postInit() {
        super.postInit();

        if (getItem().getLocationLat() != null && getItem().getLocationLng() != null) {
            marker = locationMap.createMarker();
            marker.setPosition(locationMap.createGeoPoint(
                    getItem().getLocationLat(), getItem().getLocationLng()));
            marker.setIcon(locationMap.createMarkerImage(CUSTOMER_ICON));
            locationMap.addMarker(marker);
        }
    }
}