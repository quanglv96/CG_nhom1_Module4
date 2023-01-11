// var wavesurfer = WaveSurfer.create({
//     container: '#audio-wave',
//     waveColor: 'white',
//     progressColor: 'black',
//     barWidth: 3,
//     height: 115,
//     hideScrollbar: true,
//     hideCursor: true,
// });
// wavesurfer.load('/assets/audio/Itsumo_nando_demo_Sprited_away_OST.mp3');
//
// var playBtn = $(".play-button")[0];
// var isPlay = true;
// playBtn.onclick = function() {
//     if (isPlay) {
//         $(".play-button").html('<i class="font-24 fa-solid fa-pause"></i>')
//         isPlay = false;
//     } else {
//         $(".play-button").html('<i class="font-24 fa-solid fa-play"></i>')
//         isPlay = true;
//     }
//     wavesurfer.playPause();
// }