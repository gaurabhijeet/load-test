import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class Engine {

  public static void main(String[] args) {
    GatlingPropertiesBuilder props = new GatlingPropertiesBuilder()
      .resourcesDirectory(Recorder.IDEPathHelper.mavenResourcesDirectory.toString())
      .resultsDirectory(Recorder.IDEPathHelper.resultsDirectory.toString())
      .binariesDirectory(Recorder.IDEPathHelper.mavenBinariesDirectory.toString());

    Gatling.fromMap(props.build());
  }
}
