import java.util.Comparator;

/**
 * @author ericfouh
 * @author gabsmi
 */
public interface ITerm
    extends Comparable<ITerm> {
    /**
     * Returns a comparator that compares the two terms in descending order by weight.
     * 
     * @return a comparator
     */
    public static Comparator<ITerm> byReverseWeightOrder() {
        return new Comparator<ITerm>() {
            @Override
            public int compare(ITerm term1, ITerm term2) {
                return Long.compare(term2.getWeight(), term1.getWeight());
            }
        };
    }

    // Returns the weight of the Term
    public long getWeight();

    // Compares the two terms in lexicographic order by query.
    public int compareTo(ITerm that);

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString();
}
