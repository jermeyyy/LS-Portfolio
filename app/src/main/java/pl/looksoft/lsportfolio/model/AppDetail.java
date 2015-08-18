package pl.looksoft.lsportfolio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppDetail {

        private String name;
        private String description;
        private String text;
        private String icon;
        private String background;
        private Type type;
        private List<String> gallery = new ArrayList<String>();
        private List<Link> link = new ArrayList<Link>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
