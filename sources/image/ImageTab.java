package image;

//Pour compiler : javac -cp classes:classes/affichage.jar -sourcepath sources -d classes sources/Catalogue.java
//Pour lancer : java -classpath classes:classes/affichage.jar Catalogue

public class ImageTab implements ImageGrise {
	
	private int largeur;
	private int hauteur;
	
	private NiveauGris [][] niveauGris;
	
	public ImageTab(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		niveauGris = new NiveauGris [largeur][hauteur];
		initialize(niveauGris);
	}
	
	public static void initialize(NiveauGris [][] niveauGris) {
		for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			niveauGris[i][j] = NiveauGris.BLANC;
    		}
    	}
	}
	
	/** Retourne la largeur de l'image */
    public int largeur() {
    	return largeur;
    }
    
    /** Retourne la hauteur de l'image */
    public int hauteur() {
    	return hauteur;
    }
    
    /** Retourne le niveau de gris du point de coordonnées (x,y) */
    public NiveauGris pointEn(int x, int y) {
    	return niveauGris[x][y];
    }
    
    /** Fixe le niveau de gris du point de coordonnées (x,y) à la valeur spécifiée */
    public void definirPoint(int x, int y, NiveauGris gris) {
    	niveauGris[x][y] = gris;
    }
    
    /** Met en noir le point de coordonnées (x,y) */
    public void allumer(int x, int y) {
    	while (!niveauGris[x][y].estNoir()) {
    		niveauGris[x][y] = niveauGris[x][y].assombrir();
    	}
    }
    
    /** Met en blanc le point de coordonnées (x,y) */
    public void eteindre(int x, int y) {
    	while (!niveauGris[x][y].estBlanc()) {
    		niveauGris[x][y] = niveauGris[x][y].eclaircir();
    	}
    }
    
    /** Donne une valeur aléatoire (noir ou blanc) à chaque point de l'image */
    public void randomize() {
    	for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			niveauGris[i][j] = NiveauGris.randomize();
    		}
    	}
    }
    
    /** Compte le nombre de points de l'image dont le niveau de gris est égal au niveau spécifié */
    public int compterPoints(NiveauGris gris) {
    	int compteur = 0;
    	for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			if (niveauGris[i][j].equals(gris)) {
    				compteur++;
    			}
    		}
    	}
    	return compteur;
    }
    
    /** Retourne une image qui est le négatif de l'image courante */
    public ImageGrise inverser() {
    	ImageGrise negatif = new ImageTab(largeur, hauteur);
    	for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			negatif.definirPoint(i, j, niveauGris[i][j].inverser());
    		}
    	}
    	return negatif;
    }
    
    /** Retourne une image dont tous les points (sauf blancs) sont un niveau
     * plus clair que dans l'image courante */
    public ImageGrise eclaircir() {
    	ImageGrise niveauPlusClair = new ImageTab(largeur, hauteur);
    	for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			if (!niveauGris[i][j].estBlanc()) {
    				niveauPlusClair.definirPoint(i, j, niveauGris[i][j].eclaircir());
    			}
    		}
    	}
    	return niveauPlusClair;
    }
    
    /** Retourne une image dont tous les points (sauf noirs) sont un niveau
     * plus foncé que dans l'image courante */
    public ImageGrise assombrir() {
    	ImageGrise niveauPlusFonce = new ImageTab(largeur,hauteur);
    	for (int i = 0; i < niveauGris.length; i++) {
    		for (int j = 0; j < niveauGris[i].length; j++) {
    			if (!niveauGris[i][j].estNoir()) {
    				niveauPlusFonce.definirPoint(i, j, niveauGris[i][j].assombrir());
    			}
    		}
    	}
    	return niveauPlusFonce;
    }
    
    /** Retourne une <B>copie</B> de l'image courante */
    public ImageGrise dupliquer() {
    	ImageGrise copie = this;
    	return copie;
    }
    
    /** Retourne une image en additionnant point par point les niveaux de gris de l'image 
     * courante et de l'image en paramètre (les deux images doivent être de même taille) */
    public ImageGrise ajouter(ImageGrise img) {
    	ImageGrise addition = new ImageTab(niveauGris.length, niveauGris[0].length);
    	if (img.largeur() == niveauGris.length && img.hauteur() == niveauGris.length) {
    		for (int i = 0; i < niveauGris.length; i++) {
				for (int j = 0; j < niveauGris[i].length; j++) {
					addition.definirPoint(i, j, niveauGris[i][j].ajouter(img.pointEn(i,j)));
				}
    		}
    	}
    	return addition;
    }
    
    /** Retourne une image en retranchant point par point les niveaux de gris de l'image 
     * courante et de l'image en paramètre (les deux images doivent être de même taille) */
    public ImageGrise soustraire(ImageGrise img) {
    	ImageGrise soustraction = new ImageTab(niveauGris.length, niveauGris[0].length);
    	if (img.largeur() == niveauGris.length && img.hauteur() == niveauGris.length) {
    		for (int i = 0; i < niveauGris.length; i++) {
				for (int j = 0; j < niveauGris[i].length; j++) {
					soustraction.definirPoint(i, j, niveauGris[i][j].soustraire(img.pointEn(i,j)));
				}
    		}
    	}
    	return soustraction;
    }
    
    /** Retourne une image en faisant un OU Exclusif (XOR) point par
     * point les niveaux de gris de l'image courante et de l'image en
     * paramètre (les deux images doivent être de même taille) */
    public ImageGrise XOR(ImageGrise img) {
    	ImageGrise xor = new ImageTab(niveauGris.length, niveauGris[0].length);
    	if (img.largeur() == niveauGris.length && img.hauteur() == niveauGris.length) {
    		for (int i = 0; i < niveauGris.length; i++) {
				for (int j = 0; j < niveauGris[i].length; j++) {
					xor.definirPoint(i, j, niveauGris[i][j].XOR(img.pointEn(i,j)));
				}
    		}
    	}
    	return xor;
    }
    
    /** Retourne une image qui représente "l'intersection" de l'image courante et de l'image 
     * en paramètre : seuls les points qui ont le même niveau de gris dans les deux images sont
     * conservés (les deux images doivent être de même taille) */
    public ImageGrise intersection(ImageGrise img) {
    	ImageGrise intersect = new ImageTab(niveauGris.length, niveauGris[0].length);
    	if (img.largeur() == niveauGris.length && img.hauteur() == niveauGris.length) {
    		for (int i = 0; i < niveauGris.length; i++) {
				for (int j = 0; j < niveauGris[i].length; j++) {
					if (img.pointEn(i, j).equals(pointEn(i, j))) {
						intersect.definirPoint(i, j, pointEn(i, j));
					}
				}
    		}
    	}
    	return intersect;
    }
    
    /** Retourne le niveau de gris moyen de l'image. Pour le calculer, il faut faire la 
     * moyenne des niveaux de chaque point de l'image (ce qui revient à compter combien il y
     * a de points de chaque niveau de gris possible) */
    public NiveauGris niveauMoyen() {
    	/*int [] compteurs = new int[NiveauGris.values().length];
    	
    	//On initialise le compteur
    	for (int i = 0; i < compteurs.length; i++) {
    		compteurs[i] = 0;
    	}
    	
    	for (int i = 0; i < niveauGris.length; i++) {
			for (int j = 0; j < niveauGris[i].length; j++) {
				compteurs[pointEn(i,j).ordinal()]++;		
			}
		}

		int indexLePlusGrand = compteurs[0];
		for (int i = 1; i < compteurs.length; i++) {
			if (compteurs[i] > indexLePlusGrand) {
				indexLePlusGrand = compteurs[i];
			}
		}
		return NiveauGris.deNiveau(indexLePlusGrand);*/
		int compteur = 0;
		for (int i = 0; i < niveauGris.length; i++) {
			for (int j = 0; j < niveauGris[i].length; j++) {
				compteur += pointEn(i,j).ordinal();		
			}
		}
		return NiveauGris.deNiveau(compteur/(largeur * hauteur));
    }
    
    /** Retourne une image obtenue en augmentant le contraste de l'image courante. Pour 
     * augmenter le contraste, il faut rendre les points sombres plus sombres qu'ils ne sont, 
     * et les points clairs plus clairs. Un bon moyen de procéder consiste à calculer le 
     * niveau de gris moyen de l'image, et assombrir (respectivement eclaircir) les points 
     * plus sombres (resp. plus clairs) que ce niveau moyen */
    public ImageGrise augmenterContraste() {
    	ImageGrise nouveauContraste = new ImageTab(niveauGris.length, niveauGris[0].length);
    	for (int i = 0; i < niveauGris.length; i++) {
			for (int j = 0; j < niveauGris[i].length; j++) {
				if (pointEn(i,j).ordinal() > niveauMoyen().ordinal()) {
					nouveauContraste.definirPoint(i, j, niveauMoyen().maximum(pointEn(i, j)));
				}
				else if (pointEn(i,j).ordinal() < niveauMoyen().ordinal()){
					nouveauContraste.definirPoint(i, j, niveauMoyen().minimum(pointEn(i, j)));
				}
				else {
					nouveauContraste.definirPoint(i, j, niveauMoyen());
				}
			}
    	}
    	return nouveauContraste;
    }
}
