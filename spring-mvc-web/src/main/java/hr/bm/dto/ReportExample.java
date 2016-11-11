package hr.bm.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportExample {

	protected Map<String, Object> m_reportData = new HashMap<String, Object>();
	protected String m_templateName;

	public ReportExample(String p_templateName) {
		m_templateName = p_templateName;
		addReportLabels();
		addReportData();
	}

	void addReportLabels() {
		m_reportData.put("title", "Report Tilte");
	}

	void addReportData() {
		m_reportData.put("body", "Hello world!");

		List<ListMember> items = new ArrayList<ListMember>();
		items.add(new ListMember(1, "ptica"));
		items.add(new ListMember(3, "maèka"));
		items.add(new ListMember(1, "pas"));
		m_reportData.put("items", items);
	}

	public Map<String, Object> getReportData() {
		return m_reportData;
	}

	public String getTemplateName() {
		return m_templateName;
	}

	public class ListMember {

		private int order;
		private String description;

		ListMember(int order, String description) {
			this.order = order;
			this.description = description;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
