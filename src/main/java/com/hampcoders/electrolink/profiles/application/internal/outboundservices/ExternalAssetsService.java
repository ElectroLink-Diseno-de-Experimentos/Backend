package com.hampcoders.electrolink.profiles.application.internal.outboundservices;

import com.hampcoders.electrolink.assets.interfaces.acl.InventoryContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalAssetsService implements IExternalAssetsService {
    private final InventoryContextFacade inventoryContextFacade;

    public ExternalAssetsService(InventoryContextFacade inventoryContextFacade) {
        this.inventoryContextFacade = inventoryContextFacade;
    }

    @Override
    public void createInventoryForTechnician(Long technicianProfileId) {
        if (!inventoryContextFacade.existsInventoryForTechnician(technicianProfileId)) {
            inventoryContextFacade.createInventoryForTechnician(technicianProfileId);
        }
    }
}