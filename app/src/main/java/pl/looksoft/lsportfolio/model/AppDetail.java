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

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getText() {
                return text;
        }

        public void setText(String text) {
                this.text = text;
        }

        public String getIcon() {
                return icon;
        }

        public void setIcon(String icon) {
                this.icon = icon;
        }

        public String getBackground() {
                return background;
        }

        public void setBackground(String background) {
                this.background = background;
        }

        public Type getType() {
                return type;
        }

        public void setType(Type type) {
                this.type = type;
        }

        public List<String> getGallery() {
                return gallery;
        }

        public void setGallery(List<String> gallery) {
                this.gallery = gallery;
        }

        public List<Link> getLink() {
                return link;
        }

        public void setLink(List<Link> link) {
                this.link = link;
        }

}
