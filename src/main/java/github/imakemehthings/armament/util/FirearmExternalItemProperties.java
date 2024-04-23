package github.imakemehthings.armament.util;

public enum FirearmExternalItemProperties {

    GENERIC(100),
    HANDGUN(500);

    private int durab;

    FirearmExternalItemProperties(int d) {
        this.durab = d;
    }

    public int getDurab() {
        return durab;
    }
}
