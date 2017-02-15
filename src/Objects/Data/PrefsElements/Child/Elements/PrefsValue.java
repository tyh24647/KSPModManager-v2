package Objects.Data.PrefsElements.Child.Elements;

import Utils.Validator;

import javax.annotation.Nonnull;

import static Constants.StrConstants.Characters.EMPTY;

/**
 * @author Tyler Hostager
 * @version 2/12/17
 */
public class PrefsValue {

    private Object value;

    /**
     * <b>Constructs the preferences value with a required value that cannot be null</b>
     * @param value
     */
    public PrefsValue(@Nonnull Object value) {
        if (value.getClass().equals(String.class)) {
            if (!Validator.isValidStr(value.toString())) {
                value = EMPTY;
            }
        }

        setValue(value);
    }

    private void setValue(Object value) {
        this.value = value;
    }

    public @Nonnull Object getValue() {
        return value == null ? EMPTY : value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
