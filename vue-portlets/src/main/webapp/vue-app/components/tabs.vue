<template>
  <div class="dd">
    <ul class="nav nav-tabs userNavigation" style="visibility: visible;">
      <li class="item" style="margin-left: 13%;">
        <router-link to="/">
          <i class="fas fa-check-circle"></i><strong>Idées Publiées</strong>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/ideafav">
          <i class="fas fa-heart"></i><strong>Idées Favoris</strong>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/ideadrafted">
          <i class="fas fa-pencil-alt"></i><strong>Idées Redigées</strong>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/ideaarchived">
          <i class="far fa-file-archive"></i><strong>Idées Archivées</strong>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/rating">
          <i class="fas fa-star"></i><strong>Classement des Idées</strong>
        </router-link>
      </li>
      <li class="item">
        <router-link to="/addidea">
          <i class="fas fa-plus-circle"></i><strong>Créer une Idée</strong>
        </router-link>
      </li>
    </ul>
  </div>
</template>


<style>
.dd{
  background-color: white;
}
.clLi{
  margin-left: 10%;
}
</style>
