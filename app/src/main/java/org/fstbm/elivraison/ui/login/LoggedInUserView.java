package org.fstbm.elivraison.ui.login;

import org.fstbm.elivraison.data.model.Livreur;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private Livreur livreur;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(Livreur livreur) {
        this.livreur = livreur;
    }

    public Livreur getLivreur() {
        return livreur;
    }
}