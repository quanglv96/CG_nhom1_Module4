function createWave(selector, url, playBtnSelector) {
    let wavesurfer = [];
    wavesurfer[selector] = WaveSurfer.create({
        container: selector,
        waveColor: '#333333',
        progressColor: 'rgb(210, 112, 0)',
        barWidth: 1,
        height: 110,
        hideScrollbar: true,
        cursorColor: "white"
    });
    wavesurfer[selector].load(url);

    let playBtn = $(playBtnSelector)[0];
    let isPlay = true;
    playBtn.onclick = function() {
        if (isPlay) {
            $(playBtnSelector).html('<i class="fa-solid fa-pause"></i>')
            isPlay = false;
        } else {
            $(playBtnSelector).html('<i class="fa-solid fa-play"></i>')
            isPlay = true;
        }
        wavesurfer[selector].playPause();
    }
    return wavesurfer[selector];
}

createWave('#wave-1', '/assets/audio/Itsumo_nando_demo_Sprited_away_OST.mp3', '.play-btn-1')