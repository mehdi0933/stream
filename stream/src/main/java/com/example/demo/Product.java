package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@NoArgsConstructor(force = true)

@Getter
@AllArgsConstructor
public class Product {

    private final String id;
    private final String name;
    private final String category;
    private final String brand;
    private final double price;
    private final double rating;      // Note moyenne sur 5
    private final int stock;          // Quantité en stock
    private final boolean onSale;     // Produit en promotion ?
    private final boolean available;  // Produit actuellement disponible à la vente ?
    private final int reviewCount;    // Nombre d'avis clients

    /**
     *  Redéfinition de la méthode toString pour vous aider durant vos tests.
     *  L'affichage avec System.out.println sera bien plus lisible qu'une référence d'objet type "com.package.Product@he2988hj"
     */
    @Override
    public String toString() {
        return "Product{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", category='" + this.category + '\'' +
                ", brand='" + this.brand + '\'' +
                ", price=" + this.price +
                ", rating=" + this.rating +
                ", stock=" + this.stock +
                ", onSale=" + this.onSale +
                ", available=" + this.available +
                ", reviewCount=" + this.reviewCount +
                '}';
    }

    public static void main(String[] args) {

        List<Product> products = generateFakeProductList();

        /**
         *  Point de départ pour l'exercice.
         *  Vous utiliserez la liste `products` pour réaliser les différentes consignes.
         */

        // TODO : Écrire le code des consignes ci-dessous
        products.stream().map(Product -> Product.toString());
                         //.forEach(Product -> System.out.println("tout les produits "+Product));

        List<Product> filteredProducts = products.stream()
                                                 .filter(product -> product.getPrice() <= 50)
                                                 .filter(product -> product.getCategory().equals("Informatique"))
                                                 .collect(Collectors.toList());

        //filteredProducts.forEach(Product -> System.out.println("les produits informatique de moins de 50€  "+Product));

        // ma version
        List<Product> brandsCatalogue =  products.stream().toList();
        brandsCatalogue.stream()
                       .map(brands->brands.getCategory()).distinct();
                       //.forEach(brands -> System.out.println("les marques " + brands));

        // correction IA
        List<String> brandsCatalogues =
        products.stream()
        .map(Product::getCategory)
        .distinct()
        .toList();

        //brandsCatalogue.forEach(System.out::println);

        //Calculez et affichez le prix moyen de tous les produits du catalogue.
        double moyenne = products.stream()
                                 .mapToDouble(p -> p.getPrice())
                                 .average()
                                 .orElse(0.0);
        //System.out.println("le prix moyenne " + moyenne);


        //Trouvez et affichez le produit le plus cher du catalogue.

        double priceMax = products.stream()
                                  .mapToDouble(product -> product.getPrice())
                                  .max()
                                  .orElse(0.0);
        //System.out.println("le prix le plus eleve " + priceMax);


        //Vérifiez si tous les produits marqués comme available == true ont un stock strictement supérieur à 0.

        boolean stock = products.stream()
                .filter(product -> product.isAvailable())
                .allMatch(product -> product.getStock() > 0);

        if(stock){
            //System.out.println("✅ Tous les produits disponibles ont du stock.");
        } else {
            //System.out.println("🚨 Attention : certains produits disponibles n'ont pas de stock !");
        }

        List<Product> fiveCheapestProducts = products.stream()
                                                     .sorted(Comparator.comparingDouble(Product::getPrice))
                                                     .limit(5)
                                                     .toList();

        //fiveCheapestProducts.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice()));

        List<String> modeProductNames = products.stream()
                                                .filter(product -> "Informatique".equals(product.getCategory()))
                                                .map(product -> product.getName())
                                                .sorted()
                                                .toList();
        //modeProductNames.forEach(System.out::println);


        //Créez une Collection de type Map<String, List<Product>> qui regroupe les produits par catégorie (clé = nom de la catégorie, valeur = liste des produits de cette catégorie).

        Map<String, Long> productCountByBrand = products.stream()
                                                        .collect(Collectors.groupingBy(Product::getBrand, Collectors.counting()));

        //productCountByBrand.forEach((brand, count) -> System.out.println(brand + " → " + count));



        Map<Boolean, List<Product>> partitionedBySale = products.stream()
                                                                .collect(Collectors.partitioningBy(Product::isOnSale));

       // System.out.println("Produits en promotion :");
        //partitionedBySale.get(true).forEach(System.out::println);

        //System.out.println("\nProduits non en promotion :");
        //partitionedBySale.get(false).forEach(System.out::println);


        //Trouvez un produit de la catégorie "Jeux vidéo" (n’importe lequel) dont la note (rating) est supérieure ou égale à 4.7.

        Optional<Product> highRatedGame = products.stream()
                                                  .filter(product -> "Jeux vidéo".equals(product.getCategory()))
                                                  .filter(product -> product.getRating() >= 4.7)
                                                  .findAny();

        if (highRatedGame.isPresent()) {
            //System.out.println("✅ Produit trouvé : " + highRatedGame.get().getName());
        } else {
            //System.out.println("❌ Aucun produit trouvé");
        }

        //BONUS [⭐️⭐️⭐️⭐️⭐️] : On veut estimer, par marque, le chiffre d'affaires théorique si tout le stock était vendu.

        Map<String, Double> revenueByBrand = products.stream()
                                                     .collect(Collectors.groupingBy(
                                                        Product::getBrand,
                                                        Collectors.summingDouble(product -> product.getPrice() * product.getStock())
                ));

        revenueByBrand.forEach((brand, revenue) ->
                System.out.println(brand + " rappoort " + revenue));

    }

    public static List<Product> generateFakeProductList() {
        return List.of(
                new Product("P01", "Clavier mécanique", "Informatique", "LogiTech", 89.99, 4.7, 25, true, true, 320),
                new Product("P02", "Souris gaming", "Informatique", "Razer", 59.99, 4.6, 40, false, true, 410),
                new Product("P03", "Écran 27 pouces", "Informatique", "Asus", 249.99, 4.5, 12, true, true, 190),
                new Product("P04", "Tapis de souris XXL", "Informatique", "SteelSeries", 29.99, 4.3, 60, false, true, 250),
                new Product("P05", "Chaise de bureau", "Maison", "SecretLab", 399.99, 4.8, 5, true, true, 95),
                new Product("P06", "Lampe de bureau LED", "Maison", "Philips", 39.99, 4.4, 30, false, true, 210),
                new Product("P07", "Casque audio sans fil", "Téléphonie", "Sony", 199.99, 4.6, 18, true, true, 340),
                new Product("P08", "Smartphone milieu de gamme", "Téléphonie", "Samsung", 449.99, 4.2, 8, false, true, 520),
                new Product("P09", "Housse de smartphone", "Téléphonie", "Spigen", 19.99, 4.5, 100, true, true, 610),
                new Product("P10", "Câble USB-C", "Téléphonie", "Anker", 12.99, 4.7, 200, false, true, 720),
                new Product("P11", "Basket de running", "Sport", "Nike", 89.99, 4.4, 50, true, true, 430),
                new Product("P12", "T-shirt de sport", "Sport", "Adidas", 29.99, 4.1, 80, false, true, 150),
                new Product("P13", "Haltères 10kg", "Sport", "Domyos", 49.99, 4.6, 20, false, true, 90),
                new Product("P14", "Tapis de yoga", "Sport", "Decathlon", 24.99, 4.3, 70, true, true, 270),
                new Product("P15", "Aspirateur sans fil", "Maison", "Dyson", 499.99, 4.8, 7, true, true, 610),
                new Product("P16", "Mixeur plongeant", "Cuisine", "Moulinex", 69.99, 4.2, 22, false, true, 130),
                new Product("P17", "Batterie de cuisine 10 pièces", "Cuisine", "Tefal", 159.99, 4.5, 10, true, true, 260),
                new Product("P18", "Couteau de chef", "Cuisine", "Zwilling", 79.99, 4.7, 15, false, true, 340),
                new Product("P19", "Perceuse-visseuse", "Bricolage", "Bosch", 129.99, 4.6, 14, true, true, 380),
                new Product("P20", "Boîte à outils 100 pièces", "Bricolage", "Makita", 199.99, 4.4, 9, false, true, 210),
                new Product("P21", "Peinture blanche 10L", "Bricolage", "Dulux", 59.99, 4.0, 0, false, false, 120),
                new Product("P22", "Jeu de plateau fantasy", "Jeux vidéo", "Asmodee", 49.99, 4.8, 35, true, true, 540),
                new Product("P23", "Manette sans fil", "Jeux vidéo", "Microsoft", 69.99, 4.6, 28, false, true, 430),
                new Product("P24", "Console next-gen", "Jeux vidéo", "Sony", 549.99, 4.9, 0, true, false, 980),
                new Product("P25", "Fauteuil gaming", "Maison", "DXRacer", 299.99, 4.5, 6, false, true, 260),
                new Product("P26", "Oreiller mémoire de forme", "Maison", "Emma", 79.99, 4.3, 40, true, true, 310),
                new Product("P27", "Crème hydratante", "Beauté", "Nivea", 9.99, 4.1, 120, false, true, 140),
                new Product("P28", "Sérum visage", "Beauté", "The Ordinary", 29.99, 4.6, 60, true, true, 380),
                new Product("P29", "Tondeuse barbe", "Beauté", "Philips", 59.99, 4.4, 35, false, true, 220),
                new Product("P30", "Parfum homme", "Beauté", "Dior", 89.99, 4.7, 18, true, true, 510),
                new Product("P31", "Salon de jardin", "Jardin", "AliceGarden", 399.99, 4.2, 0, false, false, 80),
                new Product("P32", "Tondeuse à gazon", "Jardin", "Bosch", 279.99, 4.5, 5, true, true, 140),
                new Product("P33", "Tuyau d’arrosage 30m", "Jardin", "Hozelock", 39.99, 4.0, 25, false, true, 90),
                new Product("P34", "Guirlande lumineuse extérieure", "Jardin", "Philips Hue", 59.99, 4.6, 15, true, true, 260),
                new Product("P35", "Manteau d’hiver", "Mode", "Uniqlo", 129.99, 4.4, 30, false, true, 300),
                new Product("P36", "Jean slim", "Mode", "Levi's", 99.99, 4.3, 50, true, true, 410),
                new Product("P37", "Pull en laine", "Mode", "Sandro", 149.99, 4.2, 12, false, true, 150),
                new Product("P38", "Chaussures de ville", "Mode", "Clarks", 109.99, 4.4, 20, true, true, 210),
                new Product("P39", "Sac à dos urbain", "Mode", "Eastpak", 59.99, 4.5, 45, false, true, 370),
                new Product("P40", "Montre connectée", "Téléphonie", "Apple", 399.99, 4.8, 9, true, true, 890)

        );
    }

}