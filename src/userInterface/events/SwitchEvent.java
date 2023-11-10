package userInterface.events;

import java.util.HashMap;

/**
 * @author Tobias Heidlund
 */
public class SwitchEvent {
    private final String activity;
    private final HashMap<String, Object> extras;


    public SwitchEvent(SwitchEventBuilder switchEventBuilder) {
        this.activity = switchEventBuilder.activity;
        this.extras = switchEventBuilder.extras;
    }

    public HashMap<String, Object> getExtras() {
        return extras;
    }

    public String getActivity() {
        return activity;
    }

    public static class SwitchEventBuilder {
        final HashMap<String, Object> extras = new HashMap<>();
        String activity;

        public SwitchEventBuilder(String activity) {
            this.activity = activity;
        }

        public SwitchEventBuilder addExtra(String key, Object val) {
            extras.put(key, val);
            return this;
        }

        public SwitchEventBuilder setActivity(String activity) {
            this.activity = activity;
            return this;
        }

        public SwitchEvent build() {
            return new SwitchEvent(this);
        }

        public SwitchEventBuilder addExtras(HashMap<String, Object> extras) {
            this.extras.putAll(extras);
            return this;
        }
    }
}
