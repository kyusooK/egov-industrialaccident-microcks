<template>
    <v-card style="width:450px; height:100%;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            휴업급여 # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            휴업급여
        </v-card-title >        

        <v-card-text>
            <Number label="AccessmentId" v-model="value.accessmentId" :editMode="editMode" :inputUI="''"/>
            <Number label="AccidentId" v-model="value.accidentId" :editMode="editMode" :inputUI="''"/>
            <String label="BusinessCode" v-model="value.businessCode" :editMode="editMode" :inputUI="''"/>
            <String label="EmployeeId" v-model="value.employeeId" :editMode="editMode" :inputUI="''"/>
            <Number label="AverageSalary" v-model="value.averageSalary" :editMode="editMode" :inputUI="''"/>
            <Number label="Period" v-model="value.period" :editMode="editMode" :inputUI="''"/>
            <String label="Status" v-model="value.status" :editMode="editMode" :inputUI="''"/>
            <Date label="Date" v-model="value.date" :editMode="editMode" :inputUI="''"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="edit"
                v-if="!editMode"
            >
                수정
            </v-btn>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                저장
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
                >
                    삭제
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openApplySalary"
            >
                ApplySalary
            </v-btn>
            <v-dialog v-model="applySalaryDiagram" width="500">
                <ApplySalaryCommand
                    @closeDialog="closeApplySalary"
                    @applySalary="applySalary"
                ></ApplySalaryCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openCreateSickLeaveBenefit"
            >
                CreateSickLeaveBenefit
            </v-btn>
            <v-dialog v-model="createSickLeaveBenefitDiagram" width="500">
                <CreateSickLeaveBenefitCommand
                    @closeDialog="closeCreateSickLeaveBenefit"
                    @createSickLeaveBenefit="createSickLeaveBenefit"
                ></CreateSickLeaveBenefitCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openRequestSickLeaveBenefit"
            >
                RequestSickLeaveBenefit
            </v-btn>
            <v-dialog v-model="requestSickLeaveBenefitDiagram" width="500">
                <RequestSickLeaveBenefitCommand
                    @closeDialog="closeRequestSickLeaveBenefit"
                    @requestSickLeaveBenefit="requestSickLeaveBenefit"
                ></RequestSickLeaveBenefitCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'AssessmentSickLeave',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            applySalaryDiagram: false,
            createSickLeaveBenefitDiagram: false,
            requestSickLeaveBenefitDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/sickLeaves'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async applySalary(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['applysalary'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeApplySalary();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openApplySalary() {
                this.applySalaryDiagram = true;
            },
            closeApplySalary() {
                this.applySalaryDiagram = false;
            },
            async createSickLeaveBenefit() {
                try {
                    if(!this.offline){
                        var temp = await axios.post(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    }

                    this.editMode = false;
                    
                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);
                
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            async requestSickLeaveBenefit(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['requestsickleavebenefit'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeRequestSickLeaveBenefit();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openRequestSickLeaveBenefit() {
                this.requestSickLeaveBenefitDiagram = true;
            },
            closeRequestSickLeaveBenefit() {
                this.requestSickLeaveBenefitDiagram = false;
            },
            async () {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href))
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
        },
    }
</script>

