<template>
    <div>
        <div class="card-deck">
            <div class="card mb-3" style="width: 18rem;"
                            v-for="(metimer, index) in metimers"
                            :key="index" >
                <div class="card-body">
                    <div class="card-header text-center text-white bg-success" :id="'Counter' + index" :timer="metimer.zeitpunkt"></div>
                    <div class="card-body">
                        <h5 class="card-title">{{ metimer.title }}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">{{ metimer.sprecher }}</h6>
                        <p class="card-text">{{ metimer.beschreibung }}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- Display the countdown timer in an element -->
</template>

<script>
import MetimerDataService from "../services/MetimerDataService";
import moment from 'moment'

export default {
  name: "metimers-list",
  data() {
    return {
      metimers: [],
      currentMetimer: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveMetimers() {
      MetimerDataService.getAll()
        .then(response => {
          this.metimers = response.data;
          this.tag= moment();
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveMetimers();
      this.currentMetimer = null;
      this.currentIndex = -1;
    }
  },
  mounted() {
    this.retrieveMetimers();
  }
};


// Update the count down every 1 second
var x = setInterval(function() {

    var textBoxes = document.querySelectorAll('[id^=Counter]');
    var timerValue;
    var timerId;
    for(var i in textBoxes){
       timerValue = textBoxes[i].getAttribute("timer");
       timerId = textBoxes[i].id;
       /* do your thing */

        // Get today's date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var distance = moment(timerValue).toDate() - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Display the result in the element with id="demo"
        document.getElementById(timerId).innerHTML = "<span>"+days+"</span><span class='badge'>Tage</span> " +
            "<span>"+hours+"</span><span class='badge'>std</span> " +
            "<span>"+minutes+"</span><span class='badge'>min</span> " +
            "<span class='display-1'>"+seconds+"</span><span class='badge'>sec</span> ";

        // If the count down is finished, write some text
        if (distance < 0) {
            clearInterval(x);
            document.getElementById(timerId).innerHTML = "EXPIRED";
        }

    } // end of Counter node for loop

}, 1000);



</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
