package com.vvs.javaee.ui.address;

import com.vvs.javaee.model.Address;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressConverter implements Converter {

    public Object coerceToUi(Object val, Component component, BindContext ctx) {
        final Address address = (Address) val;
        if (address != null) {
            return Stream.of(address.getPostIndex(),address.getCity(), address.getStreet(),
                            address.getBuilding(), address.getApartment())
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.joining(","));
        }
        return null;
    }

    public Object coerceToBean(Object val, Component component, BindContext ctx) {
        return null;
    }
}
