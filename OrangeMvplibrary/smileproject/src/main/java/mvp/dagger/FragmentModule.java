package mvp.dagger;

import com.orange.smileapp.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;
import mvp.view.IBaseView;

/**
 * 提供BaseFragment的依赖
 */
@Module
public class FragmentModule {
    private IBaseView mView;
    public FragmentModule(IBaseView mView) {
        this.mView = mView;
    }

    @FragmentScope
    @Provides
    public IBaseView provideBaseFragment() {
        return mView;
    }
}
