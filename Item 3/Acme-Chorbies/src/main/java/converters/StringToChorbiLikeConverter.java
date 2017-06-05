
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiLikeRepository;
import domain.ChorbiLike;

@Component
@Transactional
public class StringToChorbiLikeConverter implements Converter<String, ChorbiLike> {

	@Autowired
	ChorbiLikeRepository	chorbieLikeRepository;


	@Override
	public ChorbiLike convert(final String text) {
		ChorbiLike res;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				res = null;
			else {
				id = Integer.valueOf(text);
				res = this.chorbieLikeRepository.findOne(id);
			}
		} catch (final Throwable th) {
			throw new IllegalArgumentException(th);
		}

		return res;
	}

}
