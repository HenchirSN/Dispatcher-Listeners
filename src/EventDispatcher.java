// Event distributor

import java.util.HashMap;
import java.util.Map;

class EventDispatcher <T> {
    private Map<EventListener<? super T>, EventFilter<? super T>> listenerFilterMap = new HashMap<>();
    private EventDispatcher<?> nextDispatcher = null;
    private Converter<? super T, ?> converter = null;

    public void addListener(EventListener<? super T> listener, EventFilter<? super T> filter) {
        listenerFilterMap.put(listener, filter);
    }

    public void removeListener(EventListener<? super T> listener) {
        listenerFilterMap.remove(listener);
    }

    public <S> S addNextEventDispatcher(EventDispatcher<S> dispatcher, Converter<T, S> f) {
        nextDispatcher = dispatcher;
        this.converter = f;
        return (S) dispatcher;
    }

    public void send(Object event) {
        for (Map.Entry<EventListener<? super T>, EventFilter<? super T>> entry : listenerFilterMap.entrySet()) {
            EventListener<? super T> listener = entry.getKey();
            EventFilter<? super T> filter = entry.getValue();
            if (filter.pass((T) event)) {
                listener.accept((T) event);
            }
        }
        if (nextDispatcher != null) {
            nextDispatcher.send(converter.convert((T) event));
        }
    }
}





