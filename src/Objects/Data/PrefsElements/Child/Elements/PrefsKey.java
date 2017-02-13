package Objects.Data.PrefsElements.Child.Elements;

import Constants.StrConstants;
import Objects.Data.PrefsElements.Child.PrefsElement;

import javax.annotation.Nonnull;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class PrefsKey extends PrefsElement {
    private StrConstants key;

    public PrefsKey(@Nonnull String key) {
        setKey(key);
    }


}
