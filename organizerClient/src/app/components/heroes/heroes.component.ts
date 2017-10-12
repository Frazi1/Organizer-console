import {Component, OnInit} from '@angular/core';
import {Hero} from "../hero-detail/hero";
import {HeroService} from "../../services/hero-service/hero.service";
import {Router} from "@angular/router";

@Component({
  selector: 'heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit{
  title = 'app';
  selectedHero: Hero;
  heroes: Hero[];

  ngOnInit(): void {
    this.heroService.getHeroes().then(hs =>this.heroes = hs);
  }
  constructor(private heroService : HeroService,
              private router: Router){ }

  onSelect(hero: Hero) {
    this.selectedHero = hero;
  }

  private getHeroes() {
    return this.heroService.getHeroes();
  }

  goToDetail() {
    return this.router.navigate(['/detail', this.selectedHero.id]);
  }
}
