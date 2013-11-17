package ds.st;
/**
 * A specification for an unordered symbol table implementation
 */
public interface ST<Key, Value>
{
	/**
	 * Put key-value pair into the table. Remove {@code key} from the table is
	 * value is {@code null}
	 * 
	 * @param key Key
	 * @param val Value
	 */
	public void put(Key key, Value val);

	/**
	 * Get value paired with {@code key}. {@code null} is returned if key is absent
	 * @param key Key
	 * @return Value associated with {@code key}
	 */
	public Value get(Key key);

	/**
	 * gm
	 * @param key
	 */
	public void delete(Key key);

	public boolean contains(Key key);

	public boolean isEmpty();

	public int size();

	public Iterable<Key> keys();
}
