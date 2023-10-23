public interface EventFilter <T>  {
    public boolean pass(T event);
}
