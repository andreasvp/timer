<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="metimer.title"
          name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Beschreibung</label>
        <input
          class="form-control"
          id="beschreibung"
          required
          v-model="metimer.beschreibung"
          name="beschreibung"
        />
      </div>
      <div class="form-group">
        <label for="sprecher">Sprecher</label>
        <input
          class="form-control"
          id="sprecher"
          required
          v-model="metimer.sprecher"
          name="sprecher"
        />
      </div>
            <div class="form-group">
                <label for="zeitpunkt">Zeitpunkt</label>
                  <div>
                    <b-form-datepicker id="tag" v-model="metimer.tag" class="mb-2"></b-form-datepicker>
                    <b-form-timepicker id="zeit" now-button v-model="metimer.zeit" show-seconds ></b-form-timepicker>
                    <p>Tag: '{{metimer.tag}}'    -   Zeit: '{{metimer.zeit}}'</p>
                  </div>
            </div>
      <button @click="saveMetimer" class="btn btn-success">Speichern</button>
    </div>

    <div v-else>
      <h4>Speicherung erfolgreich!</h4>
    </div>
  </div>
</template>

<script>
import MetimerDataService from "../services/MetimerDataService";
import moment from 'moment'

export default {
  name: "add-metimer",
  data() {
    return {
      metimer: {
        id: null,
        title: "",
        beschreibung: "",
        sprecher: "",
        zeitpunkt: "",
        tag: moment().format("YYYY-MM-DD"),
        zeit: moment().format("kk:mm:ss")
      },
      submitted: false
    };
  },
  methods: {

    saveMetimer() {

      var data = {
        title: this.metimer.title,
        beschreibung: this.metimer.beschreibung,
        sprecher: this.metimer.sprecher,
        zeitpunkt: moment(this.metimer.tag+"T"+this.metimer.zeit)
      };

      MetimerDataService.create(data)
        .then(response => {
          this.metimer.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    newMetimer() {
      this.submitted = false;
      this.metimer = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
