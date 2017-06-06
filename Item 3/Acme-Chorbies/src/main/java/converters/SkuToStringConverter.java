
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Sku;

@Component
@Transactional
public class SkuToStringConverter implements Converter<Sku, String> {

	@Override
	public String convert(final Sku sku) {
		String res;

		if (sku == null)
			res = null;
		else
			res = String.valueOf(sku.getId());

		return res;
	}

}
