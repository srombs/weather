package com.sample.srombs.weather;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sample.srombs.weather.api.ApiInterface;
import com.sample.srombs.weather.api.ApiService;
import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.weather.ViewWeather;
import com.sample.srombs.weather.weather.ViewWeatherPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by srombs on 4/9/17.
 */
@RunWith(AndroidJUnit4.class)
public class ViewWeatherPresenterTest {

    @Inject
    ApiService apiService;

    ViewWeatherPresenter presenter;
    ViewWeather mockView;

    @Rule
    public RxSchedulersOverrideRule overrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
//        RxJavaHooks.onIOScheduler(Schedulers.immediate());

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        WeatherApplication app = (WeatherApplication) instrumentation.getTargetContext().getApplicationContext();
        TestingComponent component = (TestingComponent) app.getComponent();
        component.inject(this);

        presenter = new ViewWeatherPresenter(apiService);
        mockView = mock(ViewWeather.class);
        presenter.onAttach(mockView);

        when(apiService.getApi()).thenReturn(mock(ApiInterface.class));

    }

    @Test
    public void showShouldCurrentWeather() {

        when(apiService.getApi().getCurrentWeatherZipCode(anyString(), anyString())).thenReturn(Observable.just(setupSuccessCurrentWeather()));

        presenter.loadCurrentWeatherByZip("10001");

        verify(mockView).showLoadingIndicator();
        verify(mockView).showCurrentWeather(Matchers.any(CurrentWeather.class));
        verify(mockView).hideLoadingIndicator();
        verifyNoMoreInteractions(mockView);

    }


    @Test
    public void showShowCurrentWeatherError() {
        when(apiService.getApi().getCurrentWeatherZipCode(anyString(), anyString())).thenReturn(Observable.error(new Exception()));

        presenter.loadCurrentWeatherByZip("10001");

        verify(mockView).showLoadingIndicator();
        verify(mockView).showError();
        verify(mockView).hideLoadingIndicator();
        verifyNoMoreInteractions(mockView);

    }

    private CurrentWeather setupSuccessCurrentWeather() {
        CurrentWeather currentWeather = new CurrentWeather();
        return currentWeather;
    }





}
