
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SkuRepository;
import domain.Sku;

@Component
@Transactional
public class StringToSkuConverter implements Converter<String, Sku> {

	@Autowired
	private SkuRepository	skuRepository;


	@Override
	public Sku convert(final String text) {
		Sku result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.skuRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
