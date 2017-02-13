package Objects.Data.PrefsElements.Child.Elements;

import javax.annotation.Nonnull;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class PrefsKey {
    private String key;

    public PrefsKey(@Nonnull String key) {
        setKey(key);
    }

    public void setKey(@Nonnull String key) {
        this.key = key;
    }

    @Nonnull
    public String getKey() {
        return key;
    }

    public String toString() {
        return key;
    }
}
