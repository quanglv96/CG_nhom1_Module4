let wavesurfer = []
function createWave(id, url) {
    let containerId = '#wave-' + id
    wavesurfer[containerId] = WaveSurfer.create({
        container: containerId,
        waveColor: 'grey',
        progressColor: '#e05e2b',
        barWidth: 2,
        height: 107,
        hideScrollbar: true,
        cursorColor: "white"
    });
    wavesurfer[containerId].load(url);

    let playBtn = $(".play-btn-" + id)[0];
    let isPlay = true;
    playBtn.onclick = function() {
        if (isPlay) {
            $(".play-btn-" + id).html('<i class="fa-solid fa-pause"></i>')
            isPlay = false;
        } else {
            $(".play-btn-" + id).html('<i class="fa-solid fa-play"></i>')
            isPlay = true;
        }
        wavesurfer[containerId].playPause();
    }
}

createWave(1, '../assets/audio/Futari_no_kimochi_Inuyasha_OST.mp3',);
createWave(2, '../assets/audio/Itsumo_nando_demo_Sprited_away_OST.mp3');