<template>
    <div class="team" id="Home">
        <v-container class="my-5 backgroundTop"  elevation-20 >
            <v-layout>
                <v-flex md3 lg3 >
                    <v-img

                            src="https://1.bp.blogspot.com/-d6x0pLrpNyY/UA5Sz10gJaI/AAAAAAAAHH4/J7BPNgN2z6g/s728-e100/Idea_images.png"
                            aspect-ratio="1"
                            width="250px"
                            height="300px"
                            class="img_logo"
                    />
                </v-flex>
                <v-flex md9 lg9>
                    <h1 class=".font-weight-medium mb-4 .display-3 text-sm-left white--text">Idea eXo</h1>
                    <br />
                    <h3 class="font-weight-medium mb-4 .display-3 text-sm-left white--text">"Everything Begins With An Idea." </h3>
                </v-flex>

                <v-flex md12 lg12>
                    <div >
                         <AppMain/>

                      
                            
                    </div>
                </v-flex>

                

            </v-layout>
        </v-container>
    </div>
</template>
<script>
import AppMain from "./AppMain.vue"
export default {
  components:{AppMain}
  
}
</script>

<style>
    .img_logo{
        border-radius: 50%;
        border: 10px solid transparent;
        padding: 15px;
    }

    .backgroundTop{
        background:linear-gradient(to bottom, #1867c0, #5CBBF6);
    }
</style>