package github.imakemehthings.armament.items;

import github.imakemehthings.armament.util.FirearmExternalItemProperties;

public class Handgun extends FirearmItem { // based on the M1911

    public static final FirearmExternalItemProperties externalProperties = FirearmExternalItemProperties.HANDGUN;

    public Handgun(Properties properties) {
        super(properties);
        this.ammoLoaded = 0;
        this.maxCapacity = 7;
        this.reloadSize = maxCapacity;
        this.reloadTime = 20;
        this.shotTime = 3;
        this.xSpread = 1f;
        this.ySpread = 1f;
        this.volleyCount = 1;
        this.notes = "Good for quick burst damage and cheap manufacturing; however it falls short in terms of damage per shot and magazine capacity.";
    }
}
