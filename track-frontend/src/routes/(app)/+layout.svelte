<script lang="ts">
    import NavBar from "$lib/components/navigation/NavBar.svelte";
    let { children } = $props();
</script>

<NavBar />

{@render children?.()}