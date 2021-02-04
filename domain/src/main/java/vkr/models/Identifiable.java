package vkr.models;

import java.io.Serializable;

public interface Identifiable<ID extends Serializable> {
    ID getId();
}
