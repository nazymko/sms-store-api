package org.nazymko.moneygraph.analytics.model;

import org.nazymko.moneygraph.analytics.actions.Analyse;
import org.nazymko.moneygraph.analytics.model.support.Element;

import java.util.*;

/**
 * Created by a.nazimko on 16.03.2017.
 */
public class Processed {
    private final Sms sms;
    private final Metadata metadata;

    public Processed(Sms sms) {
        this.sms = sms;
        this.metadata = new Metadata(sms);
    }

    @Override
    public String toString() {
        return "Processed{" +
                "sms=" + sms +
                ", metadata=" + metadata +
                '}';
    }

    public Sms getSms() {
        return sms;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public class Metadata {
        HashMap<Element.Type, Set<Element>> meta = new HashMap<>();
        transient HashSet<Class<? extends Analyse>> processed = new HashSet<>();
        private Sms sms;

        public Metadata(Sms sms) {
            this.sms = sms;
        }

        @Override
        public String toString() {
            return "Metadata{" +
                    "meta=" + meta +
                    ", sms=" + sms +
                    '}';
        }

        public void executed(Class<? extends Analyse> executed) {
            processed.add(executed);
        }

        public void putMeta(Element.Type type, Element element) {
            Set<Element> elements = meta.get(type);
            if (elements == null) {
                elements = new HashSet<>();
                meta.put(type, elements);
            }

            elements.add(element);
        }

        public Set<Element> meta(Element.Type type) {

            return meta.get(type);

        }


        public List<Element> sortedMeta(Element.Type type) {
            final Set<Element> elements = meta.get(type);
            if (elements == null) {
                return Collections.emptyList();
            }
            final ArrayList<Element> sorted = new ArrayList<>(elements);
            sorted.sort(new Comparator<Element>() {
                @Override
                public int compare(Element o1, Element o2) {
                    return o1.getIndex() == o2.getIndex() ? 0 : o1.getIndex() >= o2.getIndex() ? 1 : -1;
                }
            });
            return sorted;

        }
    }
}
