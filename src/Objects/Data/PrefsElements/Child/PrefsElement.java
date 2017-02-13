package Objects.Data.PrefsElements.Child;

import Objects.Data.PrefsElements.Child.Elements.PrefsKey;
import Objects.Data.PrefsElements.Child.Elements.PrefsValue;

import javax.annotation.Nonnull;

/**
 * <p>An element which contains the key-value pair of the XMLUtils element.</p>
 * <br>
 * <b>This class provides the following functionality:</b>
 * <br>
 * <ul>
 *     <li>Setting the element key</li>
 *     <li>Setting the element value</li>
 *     <li>Retrieving the element key</li>
 *     <li>Retrieving the element value</li>
 *     <li>Creating and accessing a timestamp of when the value was last changed</li>
 * </ul>
 *
 * @see Utils.XMLUtils.XMLUtils for usage
 * @see Objects.Files.XMLFile for implementation
 *
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class PrefsElement {
    private PrefsKey key;
    private PrefsValue value;

    public PrefsElement(@Nonnull String key, @Nonnull Object value) {
        setKey(key);
        setValue(value);
    }

    public void setKey(String key) {
        this.key = new PrefsKey(key);
    }

    public PrefsKey getKey() {
        return key;
    }

    public void setValue(Object value) {
        this.value = new PrefsValue(value);
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{\"NAME\":\"" + getKey().toString() + "\",\"VALUE\":\"" + getValue().toString() + "\"}";
    }
}
