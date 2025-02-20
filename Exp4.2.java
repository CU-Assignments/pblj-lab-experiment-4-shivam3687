// Experiment 4.2: Card Collection System

import java.util.*;
class Card {
    String suit;
    String rank;
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank.equals(card.rank) && suit.equals(card.suit);
    }
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
public class CardCollectionSystem {
    private static Map<String, List<Card>> cardMap = new HashMap<>();
    private static Set<Card> cardSet = new HashSet<>();
    public static void addCard(String rank, String suit) {
        Card newCard = new Card(rank, suit);
        if (cardSet.contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
            return;
        }
        cardSet.add(newCard);
        cardMap.computeIfAbsent(suit, k -> new ArrayList<>()).add(newCard);
        System.out.println("Card added: " + newCard);
    }
    public static void findCardsBySuit(String suit) {
        List<Card> cards = cardMap.get(suit);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }
    public static void displayAllCards() {
        if (cardSet.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        for (Card card : cardSet) {
            System.out.println(card);
        }
    }
    public static void removeCard(String rank, String suit) {
        Card cardToRemove = new Card(rank, suit);
        if (!cardSet.contains(cardToRemove)) {
            System.out.println("Error: Card \"" + cardToRemove + "\" not found.");
            return;
        }
        cardSet.remove(cardToRemove);
        cardMap.get(suit).remove(cardToRemove);
        if (cardMap.get(suit).isEmpty()) {
            cardMap.remove(suit);
        }
        System.out.println("Card removed: " + cardToRemove);
    }
    public static void main(String[] args) {
        displayAllCards();
        addCard("Ace", "Spades");
        addCard("King", "Hearts");
        addCard("10", "Diamonds");
        addCard("5", "Clubs");
        findCardsBySuit("Hearts");
        findCardsBySuit("Diamonds");
        displayAllCards();
        addCard("King", "Hearts");
        removeCard("10", "Diamonds");
    }
}
