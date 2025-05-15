package ba.unsa.etf.nbp.VehicleTrackPlatform.common.converters;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

@Component
public class GenericEnumConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Enum.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) return null;

        String value = source.toString();
        Class<?> enumType = targetType.getType();

        try {
            // Try calling fromCode(int) if it exists
            Method fromCodeMethod = enumType.getMethod("fromCode", int.class);
            int code = Integer.parseInt(value);
            return fromCodeMethod.invoke(null, code);
        } catch (NoSuchMethodException e) {
            // Fallback to Enum.valueOf()
            return Enum.valueOf((Class<Enum>) enumType, value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert '" + value + "' to enum " + enumType.getSimpleName(), e);
        }
    }
}
