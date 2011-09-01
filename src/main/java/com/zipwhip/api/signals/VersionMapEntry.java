package com.zipwhip.api.signals;

/**
 * Created by IntelliJ IDEA.
 * User: jed
 * Date: 8/30/11
 * Time: 11:48 AM
 *
 * Simple way to store and update versions.
 *
 */
public class VersionMapEntry {

    private String key;
    private Long value;

    public VersionMapEntry(String key, Long value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Long getValue() {
        return value;
    }

    public Long setValue(Long value) {

        Long oldValue = this.value;
        this.value = value;

        return oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionMapEntry that = (VersionMapEntry) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}
