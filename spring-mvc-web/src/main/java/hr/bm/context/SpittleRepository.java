package hr.bm.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import hr.bm.annotation.Cold;
import hr.bm.annotation.Creamy;
import hr.bm.dto.Spittle;

@Component
@Cold
@Creamy
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
// Svaki kontroler dobije svoj:
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpittleRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Long, Spittle> spittles;

	public SpittleRepository() {
	}

	public List<Spittle> getSpittles() {
		ArrayList<Spittle> list = new ArrayList<Spittle>();
		if (spittles != null) {
			for (Spittle spittle : spittles.values()) {
				list.add(spittle);
			}
		} else {
			list.add(new Spittle(new Long("-1"), "There is no spittles!"));
		}
		return list;
	}

	@Cacheable(value = "bmCache")
	public Spittle findOne(Long id) {
		if (spittles == null) {
			return new Spittle(new Long("-1"), "There is no spittles!");
		}
		Spittle spittle = spittles.get(id);
		if (spittle == null) {
			spittle = new Spittle(new Long("-1"), "There is no spittle with id " + id + "!");
		}
		return spittle;
	}

	@CachePut(value = "bmCache", key = "#result.id", condition = "#result!=null")
	public Spittle add(Spittle spittle) {
		if (spittles == null) {
			spittles = new HashMap<Long, Spittle>();
		}
		spittles.put(spittle.getId(), spittle);
		return spittle;
	}

	@Override
	public String toString() {
		return spittles.toString();
	}

}
