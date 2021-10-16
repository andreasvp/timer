<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
          v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6" v-if="metimers">
      <h4>Metimers Liste</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(metimer, index) in metimers"
          :key="index"
          @click="setActiveMetimer(metimer, index)"
        >
          {{ metimer.title }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllMetimers">
        Remove All
      </button>
    </div>
    <div class="col-md-6" v-else>
        <h4>Keine Metimer vorhanden!</h4>
    </div>
    <div class="col-md-6">
      <div v-if="currentMetimer">
        <h4>Metimer</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentMetimer.title }}
        </div>
        <div>
          <label><strong>Beschreibung:</strong></label> {{ currentMetimer.beschreibung }}
        </div>
        <div>
          <label><strong>Sprecher:</strong></label> {{ currentMetimer.sprecher }}
        </div>
        <div>
          <label><strong>Zeitpunkt:</strong></label> {{ currentMetimer.zeitpunkt }}
        </div>

        <a class="btn btn-success"
          :href="'/metimers/' + currentMetimer.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p v-if="metimers">Bitte wähle eine Metimer...</p>
      </div>
    </div>
  </div>
</template>

<script>
import MetimerDataService from "../services/MetimerDataService";

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
    },

    setActiveMetimer(metimer, index) {
      this.currentMetimer = metimer;
      this.currentIndex = index;
    },

    removeAllMetimers() {
      MetimerDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },

    searchTitle() {
      MetimerDataService.findByTitle(this.title)
        .then(response => {
          this.metimers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveMetimers();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
