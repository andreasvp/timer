<template>
  <div v-if="currentMetimer" class="edit-form">
    <h4>Metimer</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
          v-model="currentMetimer.title"
        />
      </div>
      <div class="form-group">
        <label for="beschreibung">Beschreibung</label>
        <input type="text" class="form-control" id="beschreibung"
          v-model="currentMetimer.beschreibung"
        />
      </div>

      <div class="form-group">
        <label for="sprecher">Sprecher</label>
        <input type="text" class="form-control" id="sprecher"
          v-model="currentMetimer.sprecher"
        />
      </div>

      <div class="form-group">
        <label for="zeitpunkt">Zeitpunkt</label>
        <b-form-datepicker id="tag" v-model="currentMetimer.tag" class="mb-2"></b-form-datepicker>
        <b-form-timepicker id="zeit" now-button v-model="currentMetimer.zeit" show-seconds ></b-form-timepicker>
      </div>
    </form>

    <button class="btn btn-success"
      @click="deleteMetimer"
    >
      Delete
    </button>

    <button type="submit" class="btn btn-success"
      @click="updateMetimer"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Metimer...</p>
  </div>
</template>

<script>
import MetimerDataService from "../services/MetimerDataService";
import moment from 'moment'
export default {
  name: "metimer",
  data() {
    return {
      currentMetimer: null,
      message: ''
    };
  },
  methods: {
    getMetimer(id) {
      MetimerDataService.get(id)
        .then(response => {
          this.currentMetimer = response.data;

          this.currentMetimer.tag = moment(response.data.zeitpunkt).format("YYYY-MM-DD");
          this.currentMetimer.zeit = moment(response.data.zeitpunkt).format("kk:mm:ss");

        })
        .catch(e => {
          console.log(e);
        });
    },

    updateMetimer() {

      this.currentMetimer.zeitpunkt = moment(this.currentMetimer.tag+"T"+this.currentMetimer.zeit)

      MetimerDataService.update(this.currentMetimer.id, this.currentMetimer)
        .then(response => {
          console.log(response.data);
          this.message = 'The metimer was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteMetimer() {
      MetimerDataService.delete(this.currentMetimer.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "metimers" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getMetimer(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
