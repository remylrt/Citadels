package com.montaury.citadels.interfaceAssets;

public enum ResolutionFenetre {
    accueil(800, 400),
    parametragePartie(800, 600),
    jeu(1600, 800);

    ResolutionFenetre(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth(){return width;};


    public int getHeight(){return height;};

    private final int width;
    private final int height;

}
