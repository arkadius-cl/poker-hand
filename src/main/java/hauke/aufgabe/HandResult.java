package hauke.aufgabe;

public record HandResult<T>(Hand.Rank handRank, T payload ) {
}
