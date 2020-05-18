package ninja.seppli.lewebseite.common.service;

import java.util.function.Consumer;
import java.util.function.Function;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

/**
 * A class which initializes the {@link ModelMapper} lazy
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class LazyModelMapper {
	/**
	 * the mapper
	 */
	private ModelMapper mapper;

	/**
	 * a consumer to initialize the model mapper
	 */
	private Consumer<ModelMapper> initializer = mapper -> {};

	/**
	 * Constructor
	 */
	public LazyModelMapper() {
	}

	/**
	 * Constructor
	 * @param initializer is called when the modelMapper is created
	 */
	public LazyModelMapper(Consumer<ModelMapper> initializer) {
		this.initializer = initializer;
	}

	/**
	 * Returns the mapper. If it is the first time, the mapper
	 * is initialized and stored for the next time
	 * @return the mapper
	 */
	public ModelMapper getMapper() {
		if(mapper == null) {
			mapper = new ModelMapper();
			initializer.accept(mapper);
		}
		return mapper;
	}

	public static <S, D> Converter<S, D> createConverter(Function<S, D> fun) {
		return context -> {
			if(context.getSource() != null) {
				return fun.apply(context.getSource());
			}
			return null;
		};
	}
}
