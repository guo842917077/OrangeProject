package mvp.dagger;

import com.orange.smileapp.dagger.component.AppComponent;
import com.orange.smileapp.news.NewsFragment;

import dagger.Component;

/**
 * Fragment的基类
 */
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(NewsFragment fragment);
}
